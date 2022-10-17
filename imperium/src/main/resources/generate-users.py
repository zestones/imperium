import sys
import bcrypt

print("Type 'q' to quit")
username = ''

original_stdout = sys.stdout
with open('data.sql', 'a') as f:
    sys.stdout = f
    print ("INSERT INTO users (id, username, firstname, lastname, password) VALUES")
    sys.stdout = original_stdout

id = 0

while 1 :
    username = input("Enter a username : ")
    if (username == 'q') : 
        with open('data.sql', 'a') as f:
            sys.stdout = f
            print (";")
            sys.stdout = original_stdout
        exit()

    if (id > 0) : 
        with open('data.sql', 'a') as f:
            sys.stdout = f
            print (",")
            sys.stdout = original_stdout

    byte_pwd = username.encode('utf-8') 
    pwd = bcrypt.hashpw(byte_pwd, bcrypt.gensalt(14))
    hashed_pwd = str(pwd)
    hashed_pwd = hashed_pwd[1:]
    
    with open('data.sql', 'a') as f:
        sys.stdout = f
        print ("\t(" + str(id) + ", \'" + username + "\', \'" + username + "\', \'" + username + "\', " + hashed_pwd + ")", end='')
        sys.stdout = original_stdout
    id += 1