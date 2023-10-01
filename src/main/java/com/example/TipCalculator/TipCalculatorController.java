@Controller
public class TipCalculatorController {

    @GetMapping("/tip-calculator")
    public String showTipCalculatorForm(Model model) {
        return "tip-calculator";
    }

    @PostMapping("/tip-calculator")
    public String calculateTip(Model model, @RequestParam double billAmount, @RequestParam int tipPercentage) {
        double tipAmount = Stream.of(billAmount).map(b -> b * tipPercentage / 100).findFirst().orElse(0.0);
        double totalBillAmount = Stream.of(billAmount, tipAmount).reduce(Double::sum).orElse(0.0);

        model.addAttribute("billAmount", billAmount);
        model.addAttribute("tipPercentage", tipPercentage);
        model.addAttribute("tipAmount", tipAmount);
        model.addAttribute("totalBillAmount", totalBillAmount);

        return "tip-calculator-results";
    }
}
