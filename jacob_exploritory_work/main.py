import usb_arm
import time


def main():
    arm = usb_arm.Arm()
    arm.move(usb_arm.LedOn)
    arm.move(usb_arm.BaseClockWise)
    time.sleep(1)
    arm.move(usb_arm.CloseGrips)
    arm.move(usb_arm.GripsClose)
    arm.move(usb_arm.ElbowDown)
    arm.move(usb_arm.ElbowUp)
    arm.move(usb_arm.Stop)


if __name__ == '__main__':
    main()
