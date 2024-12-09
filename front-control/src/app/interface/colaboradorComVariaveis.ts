import { Acessos } from "./acessos";
import { CombinacaoSalarial } from "./combinacaoSalarial";
import { Computadores } from "./computadores";
import { DadosColaboradores } from "./dados-colaboradores";
import { LancamentoDp } from "./lancamentoDp";
import { VariaveisColaboradores } from "./variaveisColaboradores";

export interface ColaboradorComVariaveis {
    colaboradoresDTO: DadosColaboradores;
    acessosDTO: Acessos;
    computadoresDTO: Computadores;
    combinacaoSalarialDTO: CombinacaoSalarial;
    variaveisColaboradoresDTO: VariaveisColaboradores;
    lancamentoDpDTO: LancamentoDp;
    diferencas?: {
        diferencaBase: number;
        diferencaGratificacao: number;
        diferencaAjudaCusto: number;
        diferencaAuxilioCombustivel: number;
        diferencaAuxilioMoradia: number;
        diferencaGratificacaoFuncao: number;
      };
    totalProventos?: number;
}