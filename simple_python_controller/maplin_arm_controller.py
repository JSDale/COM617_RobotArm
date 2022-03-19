import usb_arm


# noinspection SpellCheckingInspection
class MaplinArmController:

    def __init__(self, sensitivity):
        self.__arm = usb_arm.Arm()
        self.sensitivity = sensitivity

    def set_sensitivity(self, sensitivity):
        self.sensitivity = sensitivity

    def blink_led(self):
        self.__arm.doActions(usb_arm.blink)

    def move_elbow_up(self):
        self.__arm.doActions([[usb_arm.ElbowUp, self.sensitivity]])

    def move_elbow_down(self):
        print('moving')
        self.__arm.doActions([[usb_arm.ElbowDown, self.sensitivity]])

    def move_shoulder_up(self):
        self.__arm.doActions([[usb_arm.ShoulderUp, self.sensitivity]])

    def move_shoulder_down(self):
        self.__arm.doActions([[usb_arm.ShoulderDown, self.sensitivity]])

    def move_wrist_up(self):
        self.__arm.doActions([[usb_arm.WristUp, self.sensitivity]])

    def move_wrist_down(self):
        self.__arm.doActions([[usb_arm.WristDown, self.sensitivity]])

    def rotate_arm_clockwise(self):
        self.__arm.doActions([[usb_arm.BaseClockWise, self.sensitivity]])

    def rotate_arm_counter_clockwise(self):
        self.__arm.doActions([[usb_arm.BaseCtrClockWise, self.sensitivity]])

    def close_grips(self):
        self.__arm.doActions([[usb_arm.GripsClose, self.sensitivity]])

    def open_grips(self):
        self.__arm.doActions([[usb_arm.GripsOpen, self.sensitivity]])
