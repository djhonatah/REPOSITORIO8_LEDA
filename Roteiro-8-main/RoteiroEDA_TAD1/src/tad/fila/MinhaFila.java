package tad.fila;

/**
 * Fila deve ser implementada com array fixo e estratégia circular
 * de gerenciamento de apontadores de cauda e cabeça.
 * @author fabioleite
 *
 */
public class MinhaFila implements FilaIF<Integer> {

	private int tamanho = 10; // Tamanho máximo da fila
	private int cauda = 0; // Apontador para a cauda (onde o próximo elemento será inserido)
	private int cabeca = 0; // Apontador para a cabeça (onde o próximo elemento será removido)
	private Integer[] meusDados; // Array para armazenar os dados da fila
	private int tamanhoAtual = 0; // Contador de elementos na fila

	public MinhaFila(int tamanhoInicial) {
		tamanho = tamanhoInicial;
		meusDados = new Integer[tamanho];
	}

	public MinhaFila() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException{
		if (isFull()) {
			throw new FilaCheiaException();
		}
		meusDados[cauda] = item; // Adiciona o item na posição da cauda
		cauda = (cauda + 1) % tamanho; // Move a cauda para a próxima posição (circular)
		tamanhoAtual++; // Incrementa o número de elementos na fila
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
		Integer item = meusDados[cabeca];
		meusDados[cabeca] = null;
		cabeca = (cabeca + 1) % tamanho;
		tamanhoAtual--;
		return item;
	}
	@Override
	public Integer verificarCauda() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[(cauda - 1 + tamanho) % tamanho];
	}

	@Override
	public Integer verificarCabeca() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[cabeca];
	}

	@Override
	public boolean isEmpty() {
		return tamanhoAtual == 0;
	}

	@Override
	public boolean isFull() {
		return tamanhoAtual == tamanho;
	}
}
