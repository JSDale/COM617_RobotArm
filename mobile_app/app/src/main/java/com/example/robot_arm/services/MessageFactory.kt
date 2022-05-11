package com.example.robot_arm.services

class MessageFactory {
    fun getGripMessage(direction: DirectionGrip): ByteArray {
        return when(direction) {
            DirectionGrip.Close -> "GRIPS:CLOSE".toByteArray()
            DirectionGrip.Open -> "GRIPS:OPEN".toByteArray()
        }
    }

    fun getWristMessage(direction: DirectionVertical): ByteArray {
        return when(direction) {
            DirectionVertical.Down -> "WRIST:DOWN".toByteArray()
            DirectionVertical.Up -> "WRIST:UP".toByteArray()
        }
    }

    fun getElbowMessage(direction: DirectionVertical): ByteArray {
        return when(direction) {
            DirectionVertical.Down -> "ELBOW:DOWN".toByteArray()
            DirectionVertical.Up -> "ELBOW:UP".toByteArray()
        }
    }

    fun getShoulderMessage(direction: DirectionVertical): ByteArray {
        return when(direction) {
            DirectionVertical.Down -> "SHOULDER:DOWN".toByteArray()
            DirectionVertical.Up -> "SHOULDER:UP".toByteArray()
        }
    }

    fun getRotateMessage(direction: DirectionRotate): ByteArray {
        return when(direction) {
            DirectionRotate.Left -> "ROTATE:CTRCLOCKWISE".toByteArray()
            DirectionRotate.Right -> "ROTATE:CLOCKWISE".toByteArray()
        }
    }
}
