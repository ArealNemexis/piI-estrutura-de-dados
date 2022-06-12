from sys import argv
import requests
from random import randint

for i in range(1, 1000000):
    transaction_value = randint(0, 250)
    sender = randint(1, 9500)
    destiny = randint(1, 9500)
    while (sender == destiny):
        destiny = randint(1, 9500)

    print(f"""
    INSERT INTO
    public.tb_transaction (id, destiny_user_id, finished_update_date, last_update_date, registration_date, sender_user_id, status, transaction_value)
VALUES
    ({i},{destiny},null, null,now(), {sender},2,{transaction_vdbealue});""")