meniu = ['papanasi'] * 10 + ['ceafa'] * 3 + ['guias'] * 6
preturi = [["papanasi", 7], ["ceafa", 10], ["guias", 5]]
studenti = ["Liviu", "Ion", "George", "Ana", "Florica"] 
comenzi = ["guias", "ceafa", "ceafa", "papanasi", "ceafa"] 
tavi = ["tava"] * 7
istoric_comenzi = []

comenzi_efectuate = {"papanasi": 0, "ceafa": 0, "guias": 0}
total_incasari = 0

preturi_dict = {produs: pret for produs, pret in preturi}

print("Simularea comenzilor:")
while studenti and comenzi and tavi:
    student = studenti.pop(0)
    comanda = comenzi.pop(0)
    tava = tavi.pop()

    print(f"{student} a comandat {comanda}.")

    meniu.remove(comanda)
    istoric_comenzi.append(comanda)
    comenzi_efectuate[comanda] += 1
    total_incasari += preturi_dict[comanda]

print("\nInventar final:")

print(
    f"S-au comandat {comenzi_efectuate['guias']} guias, {comenzi_efectuate['ceafa']} ceafa, {comenzi_efectuate['papanasi']} papanasi.")
print(f"Mai sunt {len(tavi)} tavi.")
print(f"Mai este ceafa: {'ceafa' in meniu}.")
print(f"Mai sunt papanasi: {'papanasi' in meniu}.")
print(f"Mai sunt guias: {'guias' in meniu}.")

print("\nFinanțe:")

print(f"Cantina a încasat: {total_incasari} lei.")
produse_sub_7_lei = [produs for produs in preturi if produs[1] <= 7]
print("Produse care costă cel mult 7 lei:", produse_sub_7_lei)
