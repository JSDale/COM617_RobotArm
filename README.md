# COM617_RobotArm

## PyUSB Library usage

### Link:
https://github.com/orionrobots/python_usb_robot_arm

### Why we need it
PyUSB allows python code to controll the robot arm over a USB connection. In order for the mobile app to communicate with the robot arm, a device is needed to control the movements of the arm via usb. This will most likely be a pi.

### Installing dirvers on Win10
During development a win10 device maybe used. In order to use PyUSB, the libusb drivers must be installed. These are outdated are not easy to find. There is an included installer that will install these drivers on Windows 10. The installer can be found in the root directory and is called `zadig-2.7.exe`. Download this application and run with admin rights. Plug in the robot arm and select the unknown device in the applications drop down box. Cycle through the `target drivers` on the right until you find `libusb-win32(v<versions may be different>)`. Click the install driver button and you should be able to use the arm!
