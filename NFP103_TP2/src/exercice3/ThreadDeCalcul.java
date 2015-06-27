package exercice3;

public class ThreadDeCalcul extends Thread {

	int i, j, m;
	int[][] mat, mat1, mat2;

	public ThreadDeCalcul(int i, int j, int m, int[][] mat, int[][] mat1,
			int[][] mat2) {
		this.i = i;
		this.j = j;
		this.m = m;
		this.mat = mat;
		this.mat1 = mat1;
		this.mat2 = mat2;
	}

	public void run() {
		for (int k = 0; k < m; k++) {
			mat[i][j] += mat1[i][k] * mat2[k][j];
		}
	}

}
