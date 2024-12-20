
import random

cuvinte = ["python", "programare", "calculator", "date", "algoritm"]
cuvant_de_ghicit = random.choice(cuvinte)
progres = ["_" for _ in cuvant_de_ghicit]

incercari_ramase = 6
litere_incercate = []

print("Cuvânt de ghicit:", " ".join(progres))
print(f"Încercări rămase: {incercari_ramase}")

while incercari_ramase > 0 and "_" in progres:
    litera = input("Introdu o literă: ").lower()

    if len(litera) != 1 or not litera.isalpha():
        print("Eroare: Introdu o literă validă!")
        continue
    if litera in litere_incercate:
        print("Ai încercat deja această literă. Încearcă alta.")
        continue

    litere_incercate.append(litera)

    if litera in cuvant_de_ghicit:
        for index, caracter in enumerate(cuvant_de_ghicit):
            if caracter == litera:
                progres[index] = litera
        print("Ai ghicit o literă!")
    else:
        incercari_ramase -= 1
        print("Litera nu este în cuvânt.")

    print("Cuvânt de ghicit:", " ".join(progres))
    print(f"Încercări rămase: {incercari_ramase}")

if "_" not in progres:
    print(f"Felicitări! Ai ghicit cuvântul: {cuvant_de_ghicit}")
else:
    print(f"Ai pierdut! Cuvântul era: {cuvant_de_ghicit}")
