import os
from time import sleep

start1 = os.system("docker start pi_es_worker_1")
print(f"""
Starting pi_es_worker_1
output
{start1}
""")
start2 = os.system("docker start pi_es_scheduler_1")
print(f"""
Starting pi_es_scheduler_1
output
{start2}
""")
sleep(120)
stop1 = os.system("docker stop pi_es_worker_1")
stop2 = os.system("docker stop pi_es_scheduler_1")
print(f"""
Stopping pi_es_worker_1
output
{stop1}
""")
print(f"""
Stopping pi_es_scheduler_1
output
{stop2}
""")
print("Finished")
