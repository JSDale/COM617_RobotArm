import bluetooth
import uuid
from message_handlers import message_handler_manager, move_elbow, move_shoulder, move_grips, move_wrist, rotate_base
from maplin_arm_controller import MaplinArmController


def main():
	handler_manager = init_handlers()
	
	print("Starting bt server")
	server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
	server_sock.bind(("", bluetooth.PORT_ANY))
	server_sock.listen(1)

	port = server_sock.getsockname()[1]
	server_uuid = str(uuid.UUID("2fda49c8-a1de-4196-aa2f-9c38ef98bf52"))
	print(server_uuid)

	bluetooth.advertise_service(server_sock, "ArmServer", service_id=server_uuid, service_classes=[server_uuid, bluetooth.SERIAL_PORT_CLASS], profiles=[bluetooth.SERIAL_PORT_PROFILE])

	print(f"Waiting for connection on RFCOMM channel {port}")

	client_sock, client_info = server_sock.accept()
	print(f"Accepted connection from {client_info}")

	try:
		while True:
			data = client_sock.recv(1024)
			if not data:
				print("No Data received - exiting")
				break
			handle_message(handler_manager, data)
	except OSError:
		pass
		
	print("disconnected")

	client_sock.close()
	server_sock.close()

	print("End")

def init_handlers():
	print("Configuring message handler")
	handler_manager = message_handler_manager.MessageHandlerManager()
	controller = MaplinArmController(0.1)
	handler_manager.add_handler(move_elbow.MoveElbow(controller))
	handler_manager.add_handler(move_shoulder.MoveShoulder(controller))
	handler_manager.add_handler(move_grips.MoveGrips(controller))
	handler_manager.add_handler(move_wrist.MoveWrist(controller))
	handler_manager.add_handler(rotate_base.RotateBase(controller))
	return handler_manager


def handle_message(handler, data):
	# Convert byte earry to string	
	message = bytes(data).decode()
	print(f"Decoded message: {message}")
	
	handler.handle(message)
	

if __name__ == "__main__":
	main()
