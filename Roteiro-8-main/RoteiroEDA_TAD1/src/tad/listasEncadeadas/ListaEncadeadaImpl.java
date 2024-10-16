package tad.listasEncadeadas;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

	private NodoListaEncadeada<T> cabeca;
	private NodoListaEncadeada<T> cauda;
	private int tamanho;

	public ListaEncadeadaImpl() {
		cabeca = new NodoListaEncadeada<>();
		cauda = new NodoListaEncadeada<>();
		cabeca.setProximo(cauda);
		tamanho = 0;
	}

	@Override
	public boolean isEmpty() {
		return tamanho == 0;
	}

	@Override
	public int size() {
		return tamanho;
	}

	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
		NodoListaEncadeada<T> corrente = cabeca;
		while (corrente.getProximo() != cauda) {
			corrente = corrente.getProximo();
		}
		corrente.setProximo(novoNo);
		novoNo.setProximo(cauda);
		tamanho++;
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) {
		if (isEmpty()) {
            try {
                throw new ListaVaziaException("Lista vazia");
            } catch (ListaVaziaException e) {
                throw new RuntimeException(e);
            }
        }
		NodoListaEncadeada<T> corrente = cabeca;
		while (corrente.getProximo() != null) {
			if (corrente.getProximo().getChave().equals(chave)) {
				NodoListaEncadeada<T> toRemove = corrente.getProximo();
				corrente.setProximo(toRemove.getProximo());
				tamanho--;
				return toRemove;
			}
			corrente = corrente.getProximo();
		}
		return null;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> corrente = cabeca.getProximo();
		while (corrente != cauda) {
			if (corrente.getChave().equals(chave)) {
				return corrente;
			}
			corrente = corrente.getProximo();
		}
		return null;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		if (tamanho == 0) {
			return null;
		}
		T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, tamanho);
		NodoListaEncadeada<T> corrente = cabeca.getProximo();
		for (int i = 0; i < tamanho; i++) {
			array[i] = corrente.getChave();
			corrente = corrente.getProximo();
		}
		return array;
	}

	@Override
	public String imprimeEmOrdem() {
		StringBuilder valores = new StringBuilder();
		NodoListaEncadeada<T> corrente = cabeca.getProximo();

		while (corrente != cauda) {
			valores.append(corrente.getChave()).append(", ");
			corrente = corrente.getProximo();
		}

		return valores.length() > 0 ? valores.substring(0, valores.length() - 2) : ""; // Retorna a string sem a vírgula final
	}

	@Override
	public String imprimeInverso() {
		StringBuilder valores = new StringBuilder();
		NodoListaEncadeada<T> corrente = cabeca.getProximo();

		while (corrente != cauda) {
			valores.insert(0, corrente.getChave() + ", "); // Adiciona no início
			corrente = corrente.getProximo();
		}

		return valores.length() > 0 ? valores.substring(0, valores.length() - 2) : ""; // Retorna a string sem a vírgula final
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		// Implementação para encontrar o sucessor
		NodoListaEncadeada<T> corrente = cabeca.getProximo();
		while (corrente != null) {
			if (corrente.getChave().equals(chave)) {
				return corrente.getProximo() != cauda ? corrente.getProximo() : null;
			}
			corrente = corrente.getProximo();
		}
		return null;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaEncadeada<T> corrente = cabeca;
		while (corrente.getProximo() != cauda) {
			if (corrente.getProximo().getChave().equals(chave)) {
				return corrente != cabeca ? corrente : null;
			}
			corrente = corrente.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave, int index) {
		if (index < 0 || index > tamanho) {
			throw new IndexOutOfBoundsException("Índice fora dos limites");
		}
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
		NodoListaEncadeada<T> corrente = cabeca;

		for (int i = 0; i < index; i++) {
			corrente = corrente.getProximo();
		}

		novoNo.setProximo(corrente.getProximo());
		corrente.setProximo(novoNo);
		tamanho++;
	}
}
