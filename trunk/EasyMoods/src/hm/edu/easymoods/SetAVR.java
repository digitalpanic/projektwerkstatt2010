package hm.edu.easymoods;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SetAVR {
	
	private InetAddress address;
	private static int port = 2702;
	private static byte rChannel = 0;
	private static byte gChannel = 1;
	private static byte bChannel = 2;
	private static byte scentChannel1 = 3;
	private static byte scentChannel2 = 4;
	
	public SetAVR(String ipAddress) {
		try {
			this.address = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	void setColor(int rVal, int gVal, int bVal, int dimval, StellaSetType type) {
		double[] rgbVals = convert(rVal, gVal, bVal, dimval);
		
		byte setType=0;
		if (type == StellaSetType.STELLA_SET_IMMEDIATELY) {
			setType = 0;
		} else if (type == StellaSetType.STELLA_SET_FADE) {
			setType = 1;
		} else if (type == StellaSetType.STELLA_SET_FLASHY) {
			setType = 2;
		} else if (type == StellaSetType.STELLA_SET_IMMEDIATELY_RELATIVE) {
			setType = 3;
		} else if (type == StellaSetType.STELLA_GETALL) {
			setType = 4;
		}
		
		
		//set R Value
		byte[] rData = new byte[3];
		rData[ 0 ] = setType; // Type
		rData[ 1 ] = this.rChannel; // Channel
		rData[ 2 ] = (byte) rgbVals[0]; // Value
		
		//set G Value
		byte[] gData = new byte[3];
		gData[ 0 ] = setType; // Type
		gData[ 1 ] = this.gChannel; // Channel
		gData[ 2 ] = (byte) rgbVals[1]; // Value
		
		//set B Value
		byte[] bData = new byte[3];
		bData[ 0 ] = setType; // Type
		bData[ 1 ] = this.bChannel; // Channel
		bData[ 2 ] = (byte) rgbVals[2]; // Value

 		DatagramSocket socket;
		try {
		
			socket = new DatagramSocket();
			DatagramPacket rPacket = new DatagramPacket(rData, rData.length, 
			                                           address, port);
			socket.send(rPacket);
			
			DatagramPacket gPacket = new DatagramPacket(gData, gData.length,
															address, port);
			socket.send(gPacket);
			
			DatagramPacket bPacket = new DatagramPacket(bData, bData.length,
					address, port);
			socket.send(bPacket);
	

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	void spray() {
		byte[] data = new byte[3];
		data[ 0 ] = 0; // Type
		data[ 1 ] = this.scentChannel1; // Channel
		data[ 2 ] = (byte) 255; // Value

 		DatagramSocket socket;
		try {
		
			socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(data, data.length, 
			                                           address, port);
			socket.send(packet);
	
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private double[] convert(int rVal, int gVal, int bVal, int dimval) {
		double[] convertedRGB = new double[3];
		
		double R = (double)rVal / 255;
		double G = (double)gVal / 255;
		double B = (double)bVal / 255;
		
		
		//First convert RGB to HSV with specified dimval as V
		double V =(double)dimval / 255;
		
		double max = Math.max(R, G);
		max = Math.max(max, B);
		
		double min = Math.min(R, G);
		min = Math.min(min, B);
		
		double S = (max - min) / max;
		Double H=null;
		if (S != 0) {
			double delta = max - min;
		    if (R == max) {
		    	H = (G - B) / delta;
		    } else if (G == max) {
		    	H = 2 + ((B - R) / delta);
		    } else if (B == max) {
		    	H = 4 + ((R - G) / delta);
		    }
		  
		    H = H*60;
		    
		    if (H < 0) {
		    	H = H + 360;
		    }
			
			
		}
		
		//Now convert HSV back to RGB
		if (H != null) {
			if (H == 360) {
				H = 0.0;
			}
			H = H / 60;
			double i= Math.floor(H);
			double f = H - i;
			double p = V*(1-S);
			double q = V*(1-(S*f));
			double t = V*(1 - (S * (1-f)));

			 if (i == 0) {
				 R = V;
				 G = t;
				 B = p;
			 } else if (i == 1) {
				 R = q;
				 G = V;
				 B = p;
			 } else if (i == 2) {
				 R = p;
				 G = V;
				 B = t;
			 } else if (i == 3) {
				 R = p;
				 G = q;
				 B = V;
			 } else if (i == 4) {
				 R = t;
				 G = p;
				 B = V;
			 } else if (i == 5) {
				 R = V;
				 G = p; 
				 B = q;
			 }

		} else {
			R = max;
			G = max;
			B = max;
		}
		
		convertedRGB[0] = R * (double)255;
		convertedRGB[1] = G * (double)255;
		convertedRGB[2] = B * (double)255;
		
		
		
		return convertedRGB;
	}
}


/*RGB to HSV (Foley and VanDam)

  max = maximum of RGB
  min = minimum of RGB

  V = max
  S = (max - min) / max

  if S = 0, H is undefined, else
    delta = max-min

    if R = max, H = (G-b)/delta
    if G = max, H = 2 + (B-R)/delta
    if B = max, H = 4 + (R-G)/delta

    H = H*60
    if H < 0, H = H + 360

HSV to RGB (Foley and VanDam)

  if S = 0 and H = undefined, R = G = B = V

  if H = 360, H = 0
  H = H / 60
  i = floor(H)
  f = H - i
  p = V*(1-S)
  q = V*(1-(S*f))
  t = V*(1 - (S * (1-f)))

  if i = 0, R = v, G = t, B = p
  if i = 1, R = q, G = v, B = p
  if i = 2, R = p, G = v, B = t
  if i = 3, R = p, G = q, B = v
  if i = 4, R = t, G = p, B = v
  if i = 5, R = v, G = p, B = q


where floor is the C floor function.
 * 
 * 
 */
