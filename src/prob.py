
p1f = open('1prob.txt', 'r')
p2f = open('2prob.txt', 'r')
p3f = open('3prob.txt', 'r')

p1lines = p1f.readlines()
p2lines = p2f.readlines()
p3lines = p3f.readlines()

p1 = []
p2 = []
p3 = []

for line in p1lines:
	spl = line[:-1].split(' ')
	p1.append("new int[]{" + spl[0] + ", " + spl[1][:-1] + ", "+ spl[2] + "}, ")

for line in p2lines:
	spl = line[:-1].split(' ')
	p2.append("new int[]{" + spl[0] + ", "+ spl[1] + ", " + spl[2][:-1] + ", "+ spl[3] + "}, ")


for line in p3lines:
	spl = line[:-1].split(' ')
	p3.append("new int[]{" + spl[0] + ", "+ spl[1] + ", " + spl[2] + ", "+ spl[3][:-1] + ","+ spl[4] + "}, ")

print("".join(p1))
print()
print()
print()
print("".join(p2))
print()
print()
print()
print()
print("".join(p3))