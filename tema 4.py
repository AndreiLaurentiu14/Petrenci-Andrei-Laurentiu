# 1. Lista de cuvinte și alegerea cuvântului la întâmplare
import random

cuvinte = ["python", "programare", "calculator", "date", "algoritm"]
cuvant_de_ghicit = random.choice(cuvinte)
progres = ["_" for _ in cuvant_de_ghicit]

# 2. Inițializarea numărului de încercări
incercari_ramase = 6
litere_incercate = []

# Afișarea șablonului inițial
print("Cuvânt de ghicit:", " ".join(progres))
print(f"Încercări rămase: {incercari_ramase}")

# Buclă principală de joc
while incercari_ramase > 0 and "_" in progres:
    # a. Cererea unei litere
    litera = input("Introdu o literă: ").lower()

    # b. Verificarea validității
    if len(litera) != 1 or not litera.isalpha():
        print("Eroare: Introdu o literă validă!")
        continue
    if litera in litere_incercate:
        print("Ai încercat deja această literă. Încearcă alta.")
        continue

    # Adăugarea literei la lista de litere încercate
    litere_incercate.append(litera)

    # c. Verificarea literei în cuvânt
    if litera in cuvant_de_ghicit:
        for index, caracter in enumerate(cuvant_de_ghicit):
            if caracter == litera:
                progres[index] = litera
        print("Ai ghicit o literă!")
    else:
        incercari_ramase -= 1
        print("Litera nu este în cuvânt.")

    # Afișarea progresului și a încercărilor rămase
    print("Cuvânt de ghicit:", " ".join(progres))
    print(f"Încercări rămase: {incercari_ramase}")

# Încheierea jocului
if "_" not in progres:
    print(f"Felicitări! Ai ghicit cuvântul: {cuvant_de_ghicit}")
else:
    print(f"Ai pierdut! Cuvântul era: {cuvant_de_ghicit}")
