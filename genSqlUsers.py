for i in range(1, 10000):
    print(f"""INSERT INTO 
    public.tb_bank_user (id, balance, registration_date)
VALUES
    ({i},1000,now());""")



