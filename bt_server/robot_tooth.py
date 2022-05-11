import bluetooth
import uuid

print("Scanning for bluetooth devices:")
devices = bluetooth.discover_devices(lookup_names=True, lookup_class = True)

device_count = len(devices)
print(f"{device_count} devices found")

for addr, name, device_class in devices:
	print("Device:")
	print(f"  - Name:    {name}")
	print(f"  - Address: {addr}")
	print(f"  - Class:   {device_class}")
	print("")

server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
server_sock.bind(("", bluetooth.PORT_ANY))
server_sock.listen(1)

port = server_sock.getsockname()[1]
uuid = str(uuid.UUID("2fda49c8-a1de-4196-aa2f-9c38ef98bf52"))
print(uuid)

bluetooth.advertise_service(server_sock, "ArmServer", service_id=uuid, service_classes=[uuid, bluetooth.SERIAL_PORT_CLASS], profiles=[bluetooth.SERIAL_PORT_PROFILE])

print(f"Waiting for connection on RFCOMM channel {port}")

client_sock, client_info = server_sock.accept()
print(f"Accepted connection from {client_info}")

try:
	while True:
		data = client_sock.recv(1024)
		if not data:
			break
		print(f"Received: {data}")
except OSError:
	pass
	
print("disconnected")

client_sock.close()
server_sock.close()

print("End")
