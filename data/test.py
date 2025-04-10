# Päätin tehdä tällaisen testitiedoston pythonilla. Näyttäisi tulostavan oikean määrän eli 579 nimeä. Tämähän on hidas kun mikä, mutta sillä ei ole tässä kontekstissa merkitystä.
def readFinnishWords() -> list[str]:
    finnishWords = []
    with open("kaikkisanat.txt", "r") as file:
        for line in file:
            finnishWords.append(line.strip())

    return finnishWords

def readFinnishNames() -> list[str]:
    finnishNamesMen = []
    finnishNamesWomen = []
    with open("etunimitilasto-miehet-ensimmainen.csv") as miehet:
        for line in miehet:
            finnishNamesMen.append(line.split(";")[0].strip())

    with open("etunimitilasto-naiset-ensimmainen.csv") as naiset:
        for line in naiset:
            finnishNamesWomen.append(line.split(";")[0].strip())

    return finnishNamesMen + finnishNamesWomen


if __name__ == "__main__":
    words = list(set(readFinnishWords()))
    names = list(set(readFinnishNames()))
    i = 0

    for name in names:
        if str.lower(name) in words:
            i += 1
            print(f"{i} -- {name}")