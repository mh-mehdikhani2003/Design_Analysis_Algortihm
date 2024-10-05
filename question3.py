def swingCount(s):
    swing = 0
    ant = 0

    for i in s:
        if i == '(':
            ant -= 1
        else:
            ant += 1

            if ant > 0:
                swing += ant
    return swing

s = input()
print(swingCount(s))