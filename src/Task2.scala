object MagicSquare {
  type Grid = Array[Array[Int]]

  def isMagicSquare(grid: Grid): Boolean = {
    val magicSum = 15

    // Vérification des lignes
    for (i <- 0 until 3) {
      val rowSum = grid(i).sum
      if (rowSum != magicSum)
        return false
    }

    // Vérification des colonnes
    for (j <- 0 until 3) {
      val colSum = (0 until 3).map(i => grid(i)(j)).sum
      if (colSum != magicSum)
        return false
    }

    // Vérification des diagonales
    val diag1Sum = (0 until 3).map(i => grid(i)(i)).sum
    val diag2Sum = (0 until 3).map(i => grid(i)(2 - i)).sum
    if (diag1Sum != magicSum || diag2Sum != magicSum)
      return false

    true
  }

  def backtrack(grid: Grid, numbers: Array[Int], row: Int, col: Int): Boolean = {
    if (row == 3) {
      // Toutes les cellules ont été remplies, vérifiez si c'est un carré magique
      return isMagicSquare(grid)
    }

    if (col == 3) {
      // Fin de remplir la colonne actuelle, passez à la colonne suivante
      return backtrack(grid, numbers, row + 1, 0)
    }

    if (grid(row)(col) != 0) {
      // La cellule actuelle a une valeur fixe, passez à la cellule suivante
      return backtrack(grid, numbers, row, col + 1)
    }

    for (num <- numbers) {
      if (!numbers.contains(num))
        return false

      grid(row)(col) = num
      val updatedNumbers = numbers.filter(_ != num)

      if (backtrack(grid, updatedNumbers, row, col + 1))
        return true

      // Restaurer la valeur d'origine pour le retour en arrière
      grid(row)(col) = 0
    }

    false
  }

  def main(args: Array[String]): Unit = {
    val numbers = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val grid: Grid = Array.ofDim[Int](3, 3)

    grid(0)(2) = 2
    grid(1)(1) = 5

    if (backtrack(grid, numbers, 0, 0)) {
      println("Soluzione trovata:")
      for (row <- grid) {
        for (value <- row) {
          print(value + " ")
        }
        println()
    }} else {
      println("Nessuna soluzione trovata.")
    }
  }
}
