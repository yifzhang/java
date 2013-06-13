package peiliping.sort;

public class Main {

	public static void main(String[] args) {

		int[] source = { 9,8,7,6,5,4,3,2,1 };
		quickSort(source, 0, source.length-1);
		for (int i : source) {
			System.out.print(i + ",");
		}
	}

	public static void quickSort(int a[], int left, int right) {
		int i, j, temp;
		i = left;
		j = right;
		if (left > right)
			return;
		temp = a[left];
		while (i != j)/* 找到最终位置 */
		{
			while (a[j] >= temp && j > i)
				j--;
			if (j > i)
				a[i++] = a[j];
			while (a[i] <= temp && j > i)
				i++;
			if (j > i)
				a[j--] = a[i];

		}
		a[i] = temp;
		quickSort(a, left, i - 1);/* 递归左边 */
		quickSort(a, i + 1, right);/* 递归右边 */
	}
}
