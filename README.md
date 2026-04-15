# ndarray-java

Mini bibliothèque Java inspirée de NumPy pour manipuler des tableaux multidimensionnels 1D et 2D.

## Membres

- Ncib Ilyes
- Taki Eddine BEN BORDI
- BenMohamed AbdelKarim

---

## Fonctionnalités

| Catégorie | Méthodes |
|---|---|
| Attributs | `ndim()`, `shape()`, `size()` |
| Création | `array(float[])`, `array(float[][])`, `zeros(int...)`, `arange(start, end)` |
| Affichage | `toString()` |
| Opérations | `add`, `subtract`, `multiply` (+ variantes `InPlace`, scalaire) |
| Forme | `reshape(int...)` avec inférence `-1` |

---

## Exemple rapide

```java
NDArray a = NDArray.array(new float[]{1f, 2f, 3f});
NDArray b = NDArray.arange(0, 3);
NDArray c = a.add(b);
System.out.println(c);          // [1.0 3.0 5.0]
System.out.println(c.ndim());   // 1

NDArray m = NDArray.arange(0, 6).reshape(2, 3);
System.out.println(m);
// [[0.0 1.0 2.0]
//  [3.0 4.0 5.0]]
```

---

## Outils

- **Git / GitHub** — versionnement et Pull Requests
- **Maven** — compilation, tests, packaging
- **JUnit 5** — tests unitaires
- **JaCoCo** — couverture de code
- **GitHub Actions** — intégration continue

---

## Workflow Git

Branche stable `main` + branches dédiées :

```
chore/bootstrap-project
feature/ndarray-core
feature/array-factories
feature/printing
feature/basic-operations
feature/reshape
docs/readme-final
```

Chaque fonctionnalité suit le cycle : **branche → PR → revue → CI verte → merge**.

---

## Lancer les tests

```bash
mvn clean verify
```

Rapport de couverture JaCoCo : `target/site/jacoco/index.html`

---

## Retour d'expérience

- Des branches petites facilitent les revues de code.
- La CI détecte rapidement les régressions.
- Écrire les tests tôt simplifie les évolutions du code.

---

## Dépôt

[https://github.com/ilyes057/PROJECT-DEVOPS](https://github.com/ilyes057/PROJECT-DEVOPS)
