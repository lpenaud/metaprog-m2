# Carnet de contact

Les définitions des fichiers xml se trouve dans le dossier `def`.

## Tests

La validation xml se fait à l'aide `xmllint`.

```
$ xmllint --noout --dtdvalid <fichier dtd> [...<fichiers xml>]
```

### Carnet
Fichier de définition : `def/carnet/canet.dtd`.

Ligne de test et sortie attendus :

```
$ xmllint --noout --dtdvalid def/carnet/carnet.dtd def/carnet/*.xml
def/carnet/testCarnet3.xml:9: element information: validity error : Element information content does not follow the DTD, expecting (libelle , texte), got (football )
def/carnet/testCarnet3.xml:10: element football: validity error : No declaration for element football
Document def/carnet/testCarnet3.xml does not validate against def/carnet/carnet.dtd
```

Le fichier 3 n'est pas valide puisque celui-ci a un élément `football` au lieu de `libelle` et `texte`.

### CarnetAttr
Fichier de définition : `def/carnetAttr/canetAttr.dtd`.

Ligne de test et sortie attendus :

```
$ xmllint --noout --dtdvalid def/carnetAttr/carnetAttr.dtd def/carnetAttr/*.xml
def/carnetAttr/testCarnetAttr2.xml:5: element information: validity error : Element information does not carry attribute libelle
def/carnetAttr/testCarnetAttr2.xml:5: element information: validity error : Element information does not carry attribute texte
def/carnetAttr/testCarnetAttr2.xml:5: element information: validity error : No declaration for attribute football of element information
Document def/carnetAttr/testCarnetAttr2.xml does not validate against def/carnetAttr/carnetAttr.dtd
```

Le fichier 3 n'est pas valide puisque celui-ci a un attribut inconuus `football` au lieu de `libelle` et `texte`.