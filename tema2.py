import string

# Declara un șir de caractere copiat de pe internet (exemplu)
text = """
România a înregistrat un nou record de infectări cu virusul SARS-CoV-2. În ultimele 24 de ore, autoritățile au raportat peste 10.000 de cazuri noi. De asemenea, numărul deceselor a crescut alarmant.
"""

# Împarte șirul în două părți egale
mid = len(text) // 2
first_part = text[:mid]
second_part = text[mid:]

# Pe prima parte:
# 1. Transformă toate literele în majuscule
first_part = first_part.upper()

# 2. Elimină toate spațiile goale de la începutul și finalul șirului
first_part = first_part.strip()

# Pe a doua parte:
# 1. Inversează ordinea caracterelor
second_part = second_part[::-1]

# 2. Transformă prima literă în majusculă
second_part = second_part.capitalize()

# 3. Elimină toate caracterele de punctuație
second_part = second_part.translate(str.maketrans('', '', string.punctuation))

# Combină cele două părți și afișează rezultatul
result = first_part + " " + second_part
print(result)