from sys import argv
import requests
from random import randint


def generate_transaction(max: int):
    sender = randint(0, max)
    destiny = randint(0, max)
    while (sender == destiny):
        destiny = randint(0, max)
    transaction = {
        "senderID": sender,
        "destinyID": destiny,
        "transactionValue": randint(0, 250)
    }

    return transaction


URL = 'http://localhost:8080/transaction'
try:
    NUM_OF_TRANSACTIONS = int(argv[1])
except IndexError as e:
    print('A')
else:
    for _ in range(NUM_OF_TRANSACTIONS):
        requests.post(url=URL, json=generate_transaction(10000))
