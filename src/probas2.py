
results = [0 for i in range(11)]

traites = []

for i in range(1,7):
	for j in range(1,7):
		for k in range(1,7):
			for l in range(1,7):
				a = [i,j,k,l]
				a.sort()
				if a in traites:
					continue

				c1 = i+j
				c2 = k+l
				c3 = i+k
				c4 = j+l
				c5 = i+l
				c6 = j+k

				chs = list(dict.fromkeys([c1,c2,c3,c4,c5,c6]))
				for ch in chs:
					results[ch-2]+=1

print(results)
print([round(res/6**4*100) for res in results])