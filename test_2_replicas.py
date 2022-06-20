import os
from time import sleep

qtd_workers = 2


def start_worker(worker_number):
    startW = os.system(f"docker start pi_es_worker_{worker_number}")
    print(f"""
    Starting pi_es_worker_{worker_number}
    output
    {startW}
    """)


def stop_worker(worker_number):
    startW = os.system(f"docker stop pi_es_worker_{worker_number}")
    print(f"""
    Stopping pi_es_worker_{worker_number}
    output
    {startW}
    """)


for i in range(1, qtd_workers + 1):
    start_worker(i)

start2 = os.system("docker start pi_es_scheduler_1")
print(f"""
Starting pi_es_scheduler_1
output
{start2}
""")

sleep(120)

for i in range(1, qtd_workers + 1):
    stop_worker(i)

stop2 = os.system("docker stop pi_es_scheduler_1")
print(f"""
Stopping pi_es_scheduler_1
output
{stop2}
""")
print("Finished")

# startW1 = os.system("docker start pi_es_worker_1")
# print(f"""
# Starting pi_es_worker_1
# output
# {startW1}
# """)
# startW2 = os.system("docker start pi_es_worker_2")
# print(f"""
# Starting pi_es_worker_2
# output
# {startW2}
# """)
