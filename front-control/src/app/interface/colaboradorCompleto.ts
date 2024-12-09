import { Acessos } from "./acessos";
import { CombinacaoSalarial } from "./combinacaoSalarial";
import { Computadores } from "./computadores";
import { DadosColaboradores } from "./dados-colaboradores";

export interface ColaboradorCompleto {
    colaboradoresDTO: DadosColaboradores;
    acessosDTO: Acessos;
    computadoresDTO: Computadores;
    combinacaoSalarialDTO: CombinacaoSalarial;
}