import string

text = """
România a înregistrat un nou record de infectări cu virusul SARS-CoV-2. În ultimele 24 de ore, autoritățile au raportat peste 10.000 de cazuri noi. De asemenea, numărul deceselor a crescut alarmant.
"""
mid = len(text) // 2
first_part = text[:mid]
second_part = text[mid:]

first_part = first_part.upper()

first_part = first_part.strip()

second_part = second_part[::-1]

second_part = second_part.capitalize()

second_part = second_part.translate(str.maketrans('', '', string.punctuation))

result = first_part + " " + second_part
print(result)
