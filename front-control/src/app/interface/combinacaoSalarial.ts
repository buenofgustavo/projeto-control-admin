export interface CombinacaoSalarial{
    id?: any;
    cpf: string;
    base: number;
    gratificacaoFuncao: number;
    possuiGratFunc: boolean;
    ajudaCusto: number;
    auxilioCombustivel: number;
    auxilioMoradia: number;
    comissao: boolean;
    chat: boolean;
    prv: number;
    valeTransporte: boolean;   
    valeAlimentacao: boolean;
    valeRefeicao: boolean;
    atualizado_por: string;
}