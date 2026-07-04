package br.ufscar.dc.compraevenda.client.controller;

import br.ufscar.dc.compraevenda.client.model.Produto;
import br.ufscar.dc.compraevenda.client.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public String listar(Model model) {
        try {
            List<Produto> produtos = produtoService.listarTodos();
            model.addAttribute("produtos", produtos);
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar produtos: " + e.getMessage());
        }
        return "index";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        try {
            Produto produto = produtoService.buscarPorId(id);
            model.addAttribute("produto", produto);
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar produto: " + e.getMessage());
            return "redirect:/produtos";
        }
        return "produtos/detalhe";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/criar";
    }

    @PostMapping
    public String criar(@ModelAttribute Produto produto, RedirectAttributes redirect) {
        try {
            produtoService.criar(produto);
            redirect.addFlashAttribute("success", "✅ Produto criado com sucesso!");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "❌ Erro: " + e.getMessage());
        }
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        try {
            Produto produto = produtoService.buscarPorId(id);
            model.addAttribute("produto", produto);
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar produto: " + e.getMessage());
            return "redirect:/produtos";
        }
        return "produtos/editar";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Produto produto, RedirectAttributes redirect) {
        try {
            produtoService.atualizar(id, produto);
            redirect.addFlashAttribute("success", "✅ Produto atualizado com sucesso!");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "❌ Erro: " + e.getMessage());
        }
        return "redirect:/produtos";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            produtoService.deletar(id);
            redirect.addFlashAttribute("success", "✅ Produto deletado com sucesso!");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "❌ Erro: " + e.getMessage());
        }
        return "redirect:/produtos";
    }
}
