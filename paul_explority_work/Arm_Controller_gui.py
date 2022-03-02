# Arm_Controller_gui.py
# requires PySimpleGUI 'python -m pip install pysimplegui'

import PySimpleGUI as sg

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
        # Do arm python control
        print("Forward")
    elif event == 'Back':
        # Do arm python control
        print("Back")
    elif event == 'Left':
        # Do arm python control
        print("Left")
    elif event == 'Right':
        # Do arm python control
        print("Right")
    elif event == 'Elbow Up':
        # Do arm python control
        print("Elbow Up")
    elif event == 'Elbow Down':
        # Do arm python control
        print("Elbow Down")
    elif event == 'Wrist Up':
        # Do arm python control
        print("Wrist Up")
    elif event == 'Wrist Down':
        # Do arm python control
        print("Wrist Down")
    elif event == 'Claw Open':
        # Do arm python control
        print("Claw Open")
    elif event == 'Claw Close':
        # Do arm python control
        print("Claw Close")
    

window.close()