class Solution {
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        Map<String, Integer> evalMap = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            evalMap.put(evalvars[i], evalints[i]);
        }
        List<String> tokens = tokenize(expression);
        Parser parser = new Parser(tokens, evalMap);
        Poly resultPoly = parser.parseExpr();

        return resultPoly.toList();
    }

    private List<String> tokenize(String s) {
        List<String> tokens = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; ) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*') {
                tokens.add(String.valueOf(c));
                i++;
            } else if (Character.isDigit(c)) {
                int j = i;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    j++;
                }
                tokens.add(s.substring(i, j));
                i = j;
            } else if (Character.isLowerCase(c)) {
                int j = i;
                while (j < n && Character.isLowerCase(s.charAt(j))) {
                    j++;
                }
                tokens.add(s.substring(i, j));
                i = j;
            } else {
                i++;
            }
        }
        return tokens;
    }
    static class Term {
        long coeff;
        List<String> vars;

        public Term(long coeff, List<String> vars) {
            this.coeff = coeff;
            this.vars = vars;
        }
    }
    static class Poly {
        Map<List<String>, Long> terms;

        public Poly() {
            this.terms = new HashMap<>();
        }

        public static Poly fromNumber(long val) {
            Poly p = new Poly();
            if (val != 0) {
                p.terms.put(Collections.emptyList(), val);
            }
            return p;
        }

        public static Poly fromVariable(String var) {
            Poly p = new Poly();
            p.terms.put(Collections.singletonList(var), 1L);
            return p;
        }

        public Poly add(Poly other) {
            Poly res = new Poly();
            res.terms.putAll(this.terms);
            for (Map.Entry<List<String>, Long> entry : other.terms.entrySet()) {
                long sum = res.terms.getOrDefault(entry.getKey(), 0L) + entry.getValue();
                if (sum == 0) {
                    res.terms.remove(entry.getKey());
                } else {
                    res.terms.put(entry.getKey(), sum);
                }
            }
            return res;
        }

        public Poly subtract(Poly other) {
            Poly res = new Poly();
            res.terms.putAll(this.terms);
            for (Map.Entry<List<String>, Long> entry : other.terms.entrySet()) {
                long diff = res.terms.getOrDefault(entry.getKey(), 0L) - entry.getValue();
                if (diff == 0) {
                    res.terms.remove(entry.getKey());
                } else {
                    res.terms.put(entry.getKey(), diff);
                }
            }
            return res;
        }

        public Poly multiply(Poly other) {
            Poly res = new Poly();
            for (Map.Entry<List<String>, Long> e1 : this.terms.entrySet()) {
                for (Map.Entry<List<String>, Long> e2 : other.terms.entrySet()) {
                    long coeff = e1.getValue() * e2.getValue();
                    if (coeff == 0) continue;
                    List<String> newVars = new ArrayList<>(e1.getKey().size() + e2.getKey().size());
                    newVars.addAll(e1.getKey());
                    newVars.addAll(e2.getKey());
                    Collections.sort(newVars);

                    long newCoeff = res.terms.getOrDefault(newVars, 0L) + coeff;
                    if (newCoeff == 0) {
                        res.terms.remove(newVars);
                    } else {
                        res.terms.put(newVars, newCoeff);
                    }
                }
            }
            return res;
        }

        public List<String> toList() {
            List<Term> termList = new ArrayList<>();
            for (Map.Entry<List<String>, Long> entry : terms.entrySet()) {
                if (entry.getValue() != 0) {
                    termList.add(new Term(entry.getValue(), entry.getKey()));
                }
            }

            Collections.sort(termList, (t1, t2) -> {
                if (t1.vars.size() != t2.vars.size()) {
                    return Integer.compare(t2.vars.size(), t1.vars.size());
                }
                for (int i = 0; i < t1.vars.size(); i++) {
                    int cmp = t1.vars.get(i).compareTo(t2.vars.get(i));
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return 0;
            });

            
            List<String> result = new ArrayList<>();
            for (Term t : termList) {
                StringBuilder sb = new StringBuilder();
                sb.append(t.coeff);
                for (String v : t.vars) {
                    sb.append("*").append(v);
                }
                result.add(sb.toString());
            }
            return result;
        }
    }

    static class Parser {
        private final List<String> tokens;
        private int pos;
        private final Map<String, Integer> evalMap;

        public Parser(List<String> tokens, Map<String, Integer> evalMap) {
            this.tokens = tokens;
            this.pos = 0;
            this.evalMap = evalMap;
        }

        public Poly parseExpr() {
            Poly res = parseTerm();
            while (pos < tokens.size()) {
                String op = tokens.get(pos);
                if (op.equals("+")) {
                    pos++;
                    Poly nextTerm = parseTerm();
                    res = res.add(nextTerm);
                } else if (op.equals("-")) {
                    pos++;
                    Poly nextTerm = parseTerm();
                    res = res.subtract(nextTerm);
                } else {
                    break;
                }
            }
            return res;
        }

        private Poly parseTerm() {
            Poly res = parseFactor();
            while (pos < tokens.size()) {
                String op = tokens.get(pos);
                if (op.equals("*")) {
                    pos++;
                    Poly nextFactor = parseFactor();
                    res = res.multiply(nextFactor);
                } else {
                    break;
                }
            }
            return res;
        }

        private Poly parseFactor() {
            String token = tokens.get(pos++);
            if (token.equals("(")) {
                Poly res = parseExpr();
                pos++; 
                return res;
            } else if (Character.isDigit(token.charAt(0))) {
                long val = Long.parseLong(token);
                return Poly.fromNumber(val);
            } else {
                if (evalMap.containsKey(token)) {
                    return Poly.fromNumber(evalMap.get(token));
                } else {
                    return Poly.fromVariable(token);
                }
            }
        }
    }
}