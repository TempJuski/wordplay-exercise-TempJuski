# Päätin tehdä tällaisen testitiedoston pythonilla. Näyttäisi tulostavan oikean määrän eli 579 nimeä (TÄHÄN KUULUU ETUNIMI). Tämähän on hidas kun mikä, mutta sillä ei ole tässä kontekstissa merkitystä.
# Yllä oleva on väärää tietoa oikeasta nimien määrästä. Määrä on varmistettu ja oikea on 578 nimeä, eli olin alunperin oikeassa. Jätän tämän tiedoston kuitenkin tähän olemaan.
def readFinnishWords() -> list[str]: # EI poista ekaa riviä eli tulokseen tulee myös Etunimi
    finnishWords = []
    with open("kaikkisanat.txt", "r") as file:
        for line in file:
            finnishWords.append(line.strip())

    return finnishWords

def readFinnishNames() -> list[str]: # EI poista ekaa riviä eli tulokseen tulee myös Etunimi
    finnishNamesMen = []
    finnishNamesWomen = []
    with open("etunimitilasto-miehet-ensimmainen.csv") as miehet:
        for line in miehet:
            finnishNamesMen.append(line.split(";")[0].strip())

    with open("etunimitilasto-naiset-ensimmainen.csv") as naiset:
        for line in naiset:
            finnishNamesWomen.append(line.split(";")[0].strip())

    return finnishNamesMen + finnishNamesWomen

def printNamesWithNumbering(words: list[str], names: list[str]) -> None:
    i = 0
    for name in names:
        if str.lower(name) in words:
            i += 1
            print(f"{i} -- {name}")


if __name__ == "__main__":
    words = list(set(readFinnishWords()))
    names = list(set(readFinnishNames()))
    
    printNamesWithNumbering(words, names)
#    print(f"Sanat: {len(readFinnishWords())}, Nimet: {len(readFinnishNames())}") Tämä tulostaa -> Sanat: 93086, Nimet: 15667
#    print(f"Sanat ilman tuplia: {len(words)}, Nimet ilman tuplia: {len(names)}") Tämä tulostaa -> Sanat ilman tuplia: 93086, Nimet ilman tuplia: 15170
