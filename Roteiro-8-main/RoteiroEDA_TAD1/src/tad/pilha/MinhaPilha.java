package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {

	private int tamanho = 10;
	private int cabeca = 0;
	private Integer[] meusDados = null;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		meusDados = new Integer[tamanho];
	}

	public MinhaPilha() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (cabeca == tamanho) {
			throw new PilhaCheiaException();
		}
		meusDados[cabeca] = item;
		cabeca++;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException();
		}
		cabeca--;
		Integer item = meusDados[cabeca];
		meusDados[cabeca] = null;
		return item;
	}

	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[cabeca - 1];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaCheiaException {
		if (cabeca == tamanho) {
			throw new PilhaCheiaException();
		}
		MinhaPilha novaPilha = new MinhaPilha(Math.min(k, cabeca));
		for (int i = cabeca - k; i < cabeca; i++) {
			if (i >= 0) {
				novaPilha.empilhar(meusDados[i]);
			}
		}
		return novaPilha;
	}

	@Override
	public boolean isEmpty() {
		return cabeca == 0;
	}
}
