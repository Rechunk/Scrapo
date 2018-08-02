## Note: This was solely an experimental project and is not meant for development use.

<br>

## Installation
A guide on running the scripts is soon to come.

<br>

## Basic Usage

### Initialize the Browser you want to use:

Syntax:
```
BROWSER="BROWSERNAME"
```

Currently supported browsers: "CHROME"

---

<br>

### Comments

Syntax:
```
# Singleline Comment
/
Multiline Comment
/
```

---

### Open a Website

Syntax:
```
OPEN "urltowebsite"
```

---

<br>

### Click on Elements

Syntax:
```
CLICK[index] CLASS/ID "class/id name"
```

<strong>Note: </strong>If no index is provided, it will automatically
be set to 0.

Example:
```
CLICK CLASS "readmore-btn" # Index set to 0
CLICK[2] CLASS "figures" # Index is 2
```

---

### Remove Elements

Syntax:
```
REMOVE[index] CLASS/ID "class/id name"
```

Example:
```
REMOVE CLASS "readmore-btn"
```

---
