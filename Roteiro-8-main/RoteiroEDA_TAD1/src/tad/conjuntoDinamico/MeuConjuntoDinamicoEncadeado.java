package tad.conjuntoDinamico;

class No {
	Integer valor;
	No proximo;

	public No(Integer valor) {
		this.valor = valor;
		this.proximo = null;
	}
}

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

	private No primeiro;
	private int tamanho;

	public MeuConjuntoDinamicoEncadeado() {
		this.primeiro = null;
		this.tamanho = 0;
	}

	@Override
	public void inserir(Integer item) {
		if (!contem(item)) {
			No novoNo = new No(item);
			novoNo.proximo = primeiro;
			primeiro = novoNo;
			tamanho++;
		}
	}

	@Override
	public Integer remover(Integer item) {
		No atual = primeiro;
		No anterior = null;

		while (atual != null) {
			if (atual.valor.equals(item)) {
				if (anterior == null) {
					primeiro = atual.proximo;
				} else {
					anterior.proximo = atual.proximo;
				}
				tamanho--;
				return item;
			}
			anterior = atual;
			atual = atual.proximo;
		}
		return null;
	}

	@Override
	public Integer predecessor(Integer item) {
		if (primeiro == null || primeiro.valor.equals(item))
			return null;

		No atual = primeiro;
		No anterior = null;

		while (atual != null && !atual.valor.equals(item)) {
			anterior = atual;
			atual = atual.proximo;
		}

		if (atual != null && anterior != null) {
			return anterior.valor;
		}

		return null;
	}

	@Override
	public Integer sucessor(Integer item) {
		No atual = primeiro;

		while (atual != null && !atual.valor.equals(item)) {
			atual = atual.proximo;
		}

		if (atual != null && atual.proximo != null) {
			return atual.proximo.valor;
		}

		return null;
	}

	@Override
	public int tamanho() {
		return tamanho;
	}

	@Override
	public Integer buscar(Integer item) {
		No atual = primeiro;

		while (atual != null) {
			if (atual.valor.equals(item)) {
				return item;
			}
			atual = atual.proximo;
		}

		return null;
	}

	@Override
	public Integer minimum() {
		if (primeiro == null)
			return null;

		No atual = primeiro;
		Integer min = primeiro.valor;

		while (atual != null) {
			if (atual.valor < min)
				min = atual.valor;
			atual = atual.proximo;
		}

		return min;
	}

	@Override
	public Integer maximum() {
		if (primeiro == null)
			return null;

		No atual = primeiro;
		Integer max = primeiro.valor;

		while (atual != null) {
			if (atual.valor > max)
				max = atual.valor;
			atual = atual.proximo;
		}

		return max;
	}

	private boolean contem(Integer item) {
		No atual = primeiro;

		while (atual != null) {
			if (atual.valor.equals(item)) {
				return true;
			}
			atual = atual.proximo;
		}

		return false;
	}
}