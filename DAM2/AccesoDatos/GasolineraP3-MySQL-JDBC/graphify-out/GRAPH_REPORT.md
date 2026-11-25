# Graph Report - .  (2026-11-25)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 91 nodes · 231 edges · 7 communities (2 shown, 5 thin omitted)
- Extraction: 75% EXTRACTED · 25% INFERRED · 0% AMBIGUOUS · INFERRED: 57 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `a3a66a9e`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Community 0
- Community 1
- Community 2
- Community 3
- Community 4
- Community 5
- Community 6

## God Nodes (most connected - your core abstractions)
1. `Pagos` - 29 edges
2. `Clientes` - 27 edges
3. `Terminal` - 24 edges
4. `GestionFicherosJSON` - 13 edges
5. `GestionFicheros` - 11 edges
6. `GestionFicherosCSV` - 10 edges
7. `GestionClientes` - 7 edges
8. `GestionPagos` - 4 edges
9. `MigraCSVToJSON` - 3 edges
10. `Main` - 2 edges

## Surprising Connections (you probably didn't know these)
- `GestionFicherosCSV` --implements--> `GestionFicheros`  [EXTRACTED]
  src/GestionFicherosCSV.java → src/GestionFicheros.java
- `GestionFicherosJSON` --implements--> `GestionFicheros`  [EXTRACTED]
  src/GestionFicherosJSON.java → src/GestionFicheros.java
- `GestionFicherosCSV` --references--> `Terminal`  [EXTRACTED]
  src/GestionFicherosCSV.java → src/Terminal.java
- `GestionFicherosJSON` --references--> `Terminal`  [EXTRACTED]
  src/GestionFicherosJSON.java → src/Terminal.java
- `Pagos` --references--> `Terminal`  [EXTRACTED]
  src/Pagos.java → src/Terminal.java

## Import Cycles
- None detected.

## Communities (7 total, 5 thin omitted)

### Community 1 - "Community 1"
Cohesion: 0.20
Nodes (3): Clientes, Override, GestionClientes

### Community 3 - "Community 3"
Cohesion: 0.31
Nodes (3): GestionFicherosJSON, Override, MigraCSVToJSON

## Knowledge Gaps
- **5 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Pagos` connect `Community 2` to `Community 0`, `Community 1`, `Community 3`, `Community 4`, `Community 5`, `Community 6`?**
  _High betweenness centrality (0.322) - this node is a cross-community bridge._
- **Why does `Clientes` connect `Community 1` to `Community 0`, `Community 3`, `Community 4`, `Community 5`?**
  _High betweenness centrality (0.231) - this node is a cross-community bridge._
- **Why does `Terminal` connect `Community 0` to `Community 1`, `Community 2`, `Community 3`, `Community 4`?**
  _High betweenness centrality (0.215) - this node is a cross-community bridge._