# Arm_Controller_gui.py
# requires PySimpleGUI 'python -m pip install pysimplegui'

import PySimpleGUI as sg

from message_handlers import message_handler_manager, move_elbow, move_shoulder, move_grips, move_wrist, rotate_base


layout = [
    [sg.Text("Pivot")], 
    [sg.Button('Forward', size=(8, 3)), sg.Button('Back', size=(8, 3)), sg.Button('Left', size=(8, 3)), sg.Button('Right', size=(8, 3))],
    [sg.Text("Elbow")], 
    [sg.Button('Elbow Up', size=(18, 3)),sg.Button('Elbow Down', size=(18, 3))],
    [sg.Text("Wrist")], 
    [sg.Button('Wrist Up', size=(18, 3)),sg.Button('Wrist Down', size=(18, 3))],
    [sg.Text("Claw")], 
    [sg.Button('Claw Open', size=(18, 3)),sg.Button('Claw Close', size=(18, 3))]
    ]

handler_manager = message_handler_manager.MessageHandlerManager()

handler_manager.add_handler(move_elbow.MoveElbow())
handler_manager.add_handler(move_shoulder.MoveShoulder())
handler_manager.add_handler(move_grips.MoveGrips())
handler_manager.add_handler(move_wrist.MoveWrist())
handler_manager.add_handler(rotate_base.RotateBase())

# Create the window
window = sg.Window("Arm Controller", layout)

# Create an event loop
while True:
    event, values = window.read()
    # End program if user closes window or
    # presses the OK button
    if event == sg.WIN_CLOSED:
        break
    elif event == 'Forward':
        print("Forward")
        handler_manager.handle('SHOULDER:DOWN')
    elif event == 'Back':
        print("Back")
        handler_manager.handle('SHOULDER:UP')
    elif event == 'Left':
        print("Left")
        handler_manager.handle('ROTATE:CLOCKWISE')
    elif event == 'Right':
        print("Right")
        handler_manager.handle('ROTATE:CTRCLOCKWISE')
    elif event == 'Elbow Up':
        print("Elbow Up")
        handler_manager.handle('ELBOW:UP')
    elif event == 'Elbow Down':
        print("Elbow Down")
        handler_manager.handle('ELBOW:DOWN')
    elif event == 'Wrist Up':
        print("Wrist Up")
        handler_manager.handle('WRIST:UP')
    elif event == 'Wrist Down':
        print("Wrist Down")
        handler_manager.handle('WRIST:DOWN')
    elif event == 'Claw Open':
        print("Claw Open")
        handler_manager.handle('GRIPS:OPEN')
    elif event == 'Claw Close':
        print("Claw Close")
        handler_manager.handle('GRIPS:CLOSE')

window.close()
