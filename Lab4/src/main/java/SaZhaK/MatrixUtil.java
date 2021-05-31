package SaZhaK;

import java.util.Random;

/**
 * Утилитарный класс для работы с матрицами
 */
public final class MatrixUtil {

	private MatrixUtil() {
	}

	/**
	 * Функция вывода вектора на консоль
	 */
	public static void print(double[] vector) {
		System.out.print("|");

		for (int i = 0; i < vector.length; i++) {
			System.out.print((round(vector[i], 10) >= 0 ?
					" " + round(vector[i], 10) : round(vector[i], 10) + " "));
		}
		System.out.println("|");
	}

	/**
	 * Функция вывода матрицы на консоль
	 */
	public static void print(double[][] matrix) {
		for (double[] i : matrix) {
			System.out.print("|");
			for (double j : i) {
				System.out.print((round(j, 10) >= 0 ? " " + round(j, 10) : round(j, 10)) + " ");
			}
			System.out.println("|");
		}
	}

	/**
	 * Функция для сравнения двух матриц
	 */
	public static boolean equals(double[][] matrix1, double[][] matrix2) {
		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix2.length; j++) {
				if (Math.abs(round(matrix1[i][j] - matrix2[i][j], 8)) > 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Функция для сравнения двух векторов
	 */
	public static boolean equals(double[] vector1, double[] vector2) {
		for (int i = 0; i < vector1.length; i++) {
			if (Math.abs(round(vector1[i] - vector2[i], 8)) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Функция генерации вектора заданной длины, состоящего из случайных элементов
	 */
	public static double[] generateVector(int dimension, int vectorElementModule) {
		Random random = new Random();

		double[] vector = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			vector[i] = random.nextInt() % vectorElementModule;
		}
		return vector;
	}

	/**
	 * Функция генерации вектора заданной длины, состоящего из случайных элементов
	 */
	public static double[] generateUnityVector(int dimension) {
		double[] vector = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			vector[i] = 1;
		}
		return vector;
	}

	/**
	 * Функция генерации вектора заданной длины, состоящего из случайных элементов
	 */
	public static double[] generateZeroVector(int dimension) {
		double[] vector = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			vector[i] = 0;
		}
		return vector;
	}

	/**
	 * Функция генерации матрицы заданного размера, состоящей из случайных элементов
	 */
	public static double[][] generateMatrix(int dimension, int matrixElementModule) {
		Random random = new Random();

		double[][] matrix = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrix[i][j] = random.nextInt() % matrixElementModule;
			}
		}
		return matrix;
	}

	/**
	 * Функция генерации диагональной матрицы с преобладанием, имеющий заданный размера и состоящей из случайных элементов
	 */
	public static double[][] generateDiagonalPredominanceMatrix(int dimension, int matrixElementModule) {
		Random random = new Random();

		double[][] matrix = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrix[i][j] = random.nextInt() % matrixElementModule;
			}
			matrix[i][i] = random.nextInt() % matrixElementModule + matrixElementModule * dimension;
		}
		return matrix;
	}

	/**
	 * Функция генерации положительно определнной матрицы заданного размера, состоящей из случайных элементов
	 */
	public static double[][] generatePositiveDefiniteMatrix(int dimension, int matrixElementModule) {
		Random random = new Random();

		double[][] matrix = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (i == j) {
					matrix[i][j] = Math.abs(random.nextInt() % matrixElementModule);
				} else {
					matrix[i][j] = random.nextInt() % matrixElementModule;
				}
			}
		}
		return matrix;
	}

	/**
	 * Функция генерации единичной матрицы укзанного размера
	 */
	public static double[][] generateUnitMatrix(int dimension) {
		double[][] unitMatrix = new double[dimension][dimension];

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (i == j) unitMatrix[i][j] = 1;
				else unitMatrix[i][j] = 0;
			}
		}

		return unitMatrix;
	}

	/**
	 * Функция генерации матрицы заданного размера, состоящей из случайных элементов
	 */
	public static double[][] generateZeroMatrix(int dimension) {
		double[][] matrix = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrix[i][j] = 0;
			}
		}
		return matrix;
	}

	/**
	 * Функция для копирования матриц
	 */
	public static double[] copy(double[] vector) {
		double[] copy = new double[vector.length];
		for (int i = 0; i < vector.length; i++) {
			copy[i] = vector[i];
		}
		return copy;
	}

	/**
	 * Функция для копирования матриц
	 */
	public static double[][] copy(double[][] matrix) {
		int dimension = matrix.length;
		double[][] copy = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		return copy;
	}

	/**
	 * Функция транспонирования матрицы
	 */
	public static void transposeMatrix(double[][] matrix) {
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Матрица должна быть квадратной");
		}
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = i; j < matrix.length; ++j) {
				double temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}

	/**
	 * Функция сложения двух векторов
	 */
	public static double[] add(double[] vector1, double[] vector2) {
		double[] result = new double[vector1.length];

		for (int i = 0; i < vector1.length; ++i) {
			result[i] = vector1[i] + vector2[i];
		}
		return result;
	}

	/**
	 * Функция сложения двух матриц
	 */
	public static double[][] add(double[][] matrix1, double[][] matrix2) {
		double[][] result = new double[matrix1.length][matrix1[0].length];

		for (int i = 0; i < matrix1.length; ++i) {
			for (int j = 0; j < matrix1.length; ++j) {
				result[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		return result;
	}

	/**
	 * Функция вычитания двух векторов
	 */
	public static double[] subtract(double[] vector1, double[] vector2) {
		double[] result = new double[vector1.length];

		for (int i = 0; i < vector1.length; ++i) {
			result[i] = vector1[i] - vector2[i];
		}
		return result;
	}

	/**
	 * Функция вычитания двух матриц
	 */
	public static double[][] subtract(double[][] matrix1, double[][] matrix2) {
		double[][] result = new double[matrix1.length][matrix1[0].length];
		for (int i = 0; i < matrix1.length; ++i) {
			for (int j = 0; j < matrix1.length; ++j) {
				result[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		return result;
	}

	/**
	 * Функция вычитания двух матриц, начниая с указанного элемента
	 */
	public static double[][] subtractFromSpecifiedPosition(double[][] matrix1, double[][] matrix2, int start) {
		double[][] result = new double[matrix1.length][matrix1[0].length];

		for (int i = 0; i < start; i++) {
			for (int j = 0; j < start; j++) {
				result[i][j] = matrix1[i][j];
			}
		}

		for (int i = start; i < matrix1.length; ++i) {
			for (int j = start; j < matrix1.length; ++j) {
				result[i][j] = matrix1[i][j] - matrix2[i - start][j - start];
			}
		}
		return result;
	}

	/**
	 * Функция скалярного произведение векторов
	 * Kariel
	 */

	public static double dotProduct(double[] vector1, double[] vector2){
		int dimention = vector1.length;
		double result = 0;
		for(int i = 0; i < dimention; i++){
			result += vector1[i]*vector2[i];
		}
		return result;
	}

	/**
	 * Функция произведения вектора на число
	 */
	public static double[] multiply(double[] vector, double number) {
		double[] result = new double[vector.length];
		for (int i = 0; i < vector.length; i++) {
			result[i] = vector[i] * number;
		}
		return result;
	}

	/**
	 * Функция произведения вектора на вектор (столбец на строку)
	 */
	public static double[][] multiply(double[] vector1, double[] vector2) {
		int dimension = vector1.length;
		double[][] result = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				result[i][j] += vector1[i] * vector2[j];
			}
		}
		return result;
	}

	/**
	 * Функция произведения матрицы на число
	 */
	public static double[][] multiply(double[][] matrix, double number) {
		int dimension = matrix.length;
		double[][] result = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				result[i][j] += matrix[i][j] * number;
			}
		}
		return result;
	}

	/**
	 * Функция произведения матрицы на вектор
	 */
	public static double[] multiply(double[][] matrix, double[] vector) {
		int dimension = matrix.length;
		double[] result = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				result[i] += matrix[i][j] * vector[j];
			}
		}
		return result;
	}

	/**
	 * Функция перемножения транспонированого вектора на матрицу
	 * Kariel
	 */
	//TODO chek
	/*public static double[] multiply(double[] vector, double[][] main.java.matrix) {
		int dimension = main.java.matrix.length;
		double[] result = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				result[i] += vector[i] * main.java.matrix[j][i];
			}
		}
		return result;
	}*/

	/**
	 * Функция произведения двух матриц
	 */
	public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
		int dimension = matrixA.length;
		double[][] resultMatrix = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				for (int k = 0; k < dimension; k++) {
					resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}
		return resultMatrix;
	}


	/**
	 * Функция подсчёта нормы вектора
	 */
	public static double norm(double[] vector) {
		double norm;
		double sum = 0;
		for (int i = 0; i < vector.length; i++) {
			sum += Math.pow(vector[i], 2);
		}
		norm = Math.sqrt(sum);
		return norm;
	}

	/**
	 * Функция подсчёта нормы бесконечности матрицы
	 */
	public static double norm(double[][] matrix) {
		double norm = 0;
		for (int i = 0; i < matrix.length; i++) {
			double sum = 0;
			for (int j = 0; j < matrix.length; j++) {
				sum += Math.abs(matrix[i][j]);
			}
			norm = Math.max(norm, sum);
		}
		return norm;
	}

	/**
	 * Функция подсчёта нормы Фробениуса матрицы
	 */
	public static double frobeniusNorm(double[][] matrix) {
		double norm = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				norm += Math.pow(matrix[i][j], 2);
			}
		}
		return Math.sqrt(norm);
	}

	/**
	 * Функция вычисления ранга матрицы A
	 */
	public static int rang(double matrix[][]) {
		int rang = matrix.length;
		for (int row = 0; row < rang; row++) {
			if (matrix[row][row] != 0) {
				for (int col = 0; col < matrix.length; col++) {
					if (col != row) {
						double mult = matrix[col][row] / matrix[row][row];
						for (int i = 0; i < rang; i++)
							matrix[col][i] -= mult * matrix[row][i];
					}
				}
			} else {
				boolean reduce = true;
				for (int i = row + 1; i < matrix.length; i++) {
					if (matrix[i][row] != 0) {
						swap(matrix, row, i, rang);
						reduce = false;
						break;
					}
				}
				if (reduce) {
					rang--;
					for (int i = 0; i < matrix.length; i++)
						matrix[i][row] = matrix[i][rang];
				}
				row--;
			}
		}
		return rang;
	}

	/**
	 * Функция для перестановки строк до указанного элемента
	 */
	private static void swap(double mat[][], int row1, int row2, int col) {
		for (int i = 0; i < col; i++) {
			double temp = mat[row1][i];
			mat[row1][i] = mat[row2][i];
			mat[row2][i] = temp;
		}
	}


	/**
	 * Функция поиска максимального по модулю элемента матрицы
	 */
	/*
	public static MatrixElement findMax(double[][] main.java.matrix, int bound) {
		MatrixElement element = new MatrixElement();
		element.value = 0;

		for (int i = bound; i < main.java.matrix.length; i++) {
			for (int j = bound; j < main.java.matrix.length; j++) {
				if (Math.abs(main.java.matrix[i][j]) > Math.abs(element.value)) {
					element.value = main.java.matrix[i][j];
					element.row = i;
					element.column = j;
				}
			}
		}

		return element;
	}*/

	/**
	 * Функция перестановки местами строк матрицы
	 */
	public static void swapRows(double[][] matrix, int sourceRow, int targetRow) {
		double[] temp = matrix[sourceRow];
		matrix[sourceRow] = matrix[targetRow];
		matrix[targetRow] = temp;
	}

	/**
	 * Функция перестановки местами столбцов матрицы
	 */
	public static void swapColumns(double[][] matrix, int sourceColumn, int targetColumn) {
		for (int i = 0; i < matrix.length; i++) {
			double temp = matrix[i][sourceColumn];
			matrix[i][sourceColumn] = matrix[i][targetColumn];
			matrix[i][targetColumn] = temp;
		}
	}

	/**
	 * Функция для вычитания одной строи из другой с указанным коэффициентом
	 */
	public static void subtractRows(double[][] matrix, int subtrahendRow, int minuendRow, double rate, int bound) {
		for (int j = bound; j < matrix.length; j++) {
			matrix[minuendRow][j] = round(matrix[minuendRow][j] - rate * matrix[subtrahendRow][j], 10);
		}
	}

	/**
	 * Функция для приближения резульатов вычислений
	 * Необходимость существования этой функции обусловлена тем, что любые операции с double, включая даже сложение
	 * дают неточный результат
	 * Как следствие, получить верхнюю треугольную с нулями под главной диагональю матрицу
	 * без использования округлений невозможно
	 */
	public static double round(double num, double digitsNumber) {
		return ((long) (num * Math.pow(10, digitsNumber))) / Math.pow(10, digitsNumber);
	}

	public static double[][] change(double[][] matrix, int[] vector) {
		double[][] result = new double[matrix.length][];

		for (int i = 0; i < result.length; i++) {
			result[i] = matrix[vector[i]];
		}
		return result;
	}

	public static double[] change(double[] vector, int[] vectorP) {
		double[] result = new double[vector.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = vector[vectorP[i]];
		}
		return result;
	}

	public static double[][] changeColumns(double[][] matrix, int[] vectorQ) {
		double[][] result = new double[matrix.length][matrix.length];

		for (int j = 0; j < result.length; j++) {
			for (int i = 0; i < result.length; i++) {
				result[i][j] = matrix[i][vectorQ[j]];
			}
		}
		return result;
	}
}
