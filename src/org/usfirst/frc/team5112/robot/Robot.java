package org.usfirst.frc.team5112.robot;

import org.opencv.core.Mat;

import com.kylecorry.frc.vision.MultiCameraSource;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private Mat frame;
	private VideoCamera cam0, cam1;
	private MjpegServer server;
	private CvSink cvSink;

	@Override
	public void robotInit() {
		
		cam0 = new UsbCamera("PegCamera", 0);
		cam1 = new UsbCamera("BoilerCamera", 1);

		server = new MjpegServer("serve_USB Camera 0", 1181);
		
		cvSink = new CvSink("opencv_USB Camera 0");

		switchToPegCamera();
		
		frame = new Mat();
	}

	@Override
	public void autonomousInit() {
		switchToBoilerCamera();
	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		switchToPegCamera();
	}
	
	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testPeriodic() {
	}
	
	public void switchToBoilerCamera(){
		cvSink.setSource(cam1);
		server.setSource(cam1);
	}
	
	
	public void switchToPegCamera(){
		cvSink.setSource(cam0);
		server.setSource(cam0);
	}
	
	public Mat getImage(){
		cvSink.grabFrame(frame);
		return frame;
	}
	
}

