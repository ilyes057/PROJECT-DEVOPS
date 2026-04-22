![CI](https://github.com/ilyes057/PROJECT-DEVOPS/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-3.9+-orange)
![JUnit](https://img.shields.io/badge/JUnit-5-brightgreen)
![JaCoCo](https://img.shields.io/badge/Coverage-JaCoCo-yellowgreen)
![Docker](https://img.shields.io/badge/Docker-Demo-blue)
![Terraform](https://img.shields.io/badge/Terraform-IaC-623CE4)
![Google Cloud](https://img.shields.io/badge/Google_Cloud-Deployed-4285F4)
![GHCR](https://img.shields.io/badge/GHCR-Published-success)
# ndarray-java

Mini bibliothèque Java inspirée de NumPy pour manipuler des tableaux multidimensionnels 1D et 2D.

## Membres

- NCIB Ilyes
- BEN BORDI Taki Eddine
- BENMOHAMED AbdelKarim

---

## Fonctionnalités

| Catégorie | Méthodes |
|---|---|
| Attributs | `ndim()`, `shape()`, `size()` |
| Création | `array(float[])`, `array(float[][])`, `zeros(int...)`, `ones(int...)`, `arange(...)`,   `linspace(...)` |
| Affichage | `toString()` |
| Opérations | `add`, `subtract`, `multiply`, `addInPlace`, `subtractInPlace`, `multiplyInPlace`, `multiply(float)` |
| Forme | `reshape(int...)` avec inférence `-1`, `transpose()` *(2D uniquement)* |
| Réduction | `sum()` |
| Universal function | `abs()` |
| Broadcast | support basique du broadcasting pour `add(...)` |
---

## Fonctionnalités optionnelles réalisées

En plus des fonctionnalités minimales demandées, le projet inclut aussi :

- `ones(int...)`
- `linspace(float start, float stop, int num)`
- `transpose()` pour les tableaux 2D
- `sum()`
- `abs()` comme universal function élément par élément
- support basique du broadcasting pour `add(...)`
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

## Docker

Le projet peut être démontré à l’aide d’une image Docker.

### Build local

```bash
docker build -t ndarray-java-demo .
docker run --rm ndarray-java-demo
```
---

## Publication de l'image Docker

L’image de démonstration est publiée automatiquement via GitHub Actions sur GitHub Container Registry (GHCR).

### Image publiée
```bash
docker pull ghcr.io/ilyes057/project-devops-demo:latest
docker run --rm ghcr.io/ilyes057/project-devops-demo:latest
```
---

## Infrastructure-as-Code et Cloud

Le projet inclut également un déploiement simple sur Google Cloud avec Terraform.

L’infrastructure déployée :
- crée une machine virtuelle Google Compute Engine
- installe Docker via un startup script
- récupère l’image publiée sur GHCR
- exécute automatiquement la démo de la bibliothèque

Les fichiers Terraform se trouvent dans le dossier `infra/`.
---

## CI/CD

Deux workflows GitHub Actions sont utilisés :

- `ci.yml` : compilation, tests et couverture avec `mvn verify`
- `publish-docker.yml` : construction et publication de l’image Docker de démonstration sur GHCR
---
## Outils

- **Git / GitHub** — versionnement et Pull Requests
- **Maven** — compilation, tests, packaging
- **JUnit 5** — tests unitaires
- **JaCoCo** — couverture de code
- **GitHub Actions** — intégration continue
- **Docker** — démonstration, conteneurisation et publication d’image
- **Terraform** — infrastructure-as-code pour le déploiement cloud
- **Google Cloud** — exécution de la démonstration sur VM

---

## Workflow Git

Le développement suit un workflow simple basé sur :

- une branche stable `main`
- des branches dédiées par fonctionnalité
- des Pull Requests relues par un autre membre
- une CI verte avant merge

Cycle suivi :
**branche → développement → tests → Pull Request → review → CI verte → merge**
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
