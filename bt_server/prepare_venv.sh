#!/bin/sh
python -m venv venv
. venv/bin/activate

python -m pip install --upgrade pip
pip install -r requirements.txt

deactivate
