from sys import argv
import requests

URL = 'http://localhost:8080/bankUser'
try:
    NUM_OF_USERS = int(argv[1])
except IndexError as e:
    print('A')
else:
    for _ in range(NUM_OF_USERS):
       requests.post(url=URL, json={"balance": 1000})
