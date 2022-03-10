import usb_arm


# noinspection SpellCheckingInspection
class MaplinArmController:

    def __init__(self):
        self.__arm = usb_arm.Arm()

    def blink_led(self):
        self.__arm.move(usb_arm.blink)

    def move_elbow_up(self):
        self.__arm.move(usb_arm.ElbowUp)

    def move_elbow_down(self):
        self.__arm.move(usb_arm.ElbowDown)

    def move_shoulder_up(self):
        self.__arm.move(usb_arm.ShoulderUp)

    def move_shoulder_down(self):
        self.__arm.move(usb_arm.ShoulderDown)

    def move_wrist_up(self):
        self.__arm.move(usb_arm.WristUp)

    def move_wrist_down(self):
        self.__arm.move(usb_arm.WristDown)

    def rotate_arm_clockwise(self):
        self.__arm.move(usb_arm.BaseClockWise)

    def rotate_arm_counter_clockwise(self):
        self.__arm.move(usb_arm.BaseCtrClockWise)

    def close_grips(self):
        self.__arm.move(usb_arm.GripsClose)

    def open_grips(self):
        self.__arm.move(usb_arm.GripsOpen)
