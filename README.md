# acac-example-clj
Example of a Clojure web-app using Access-Control-Allow-Credentials header to access domain-wide cookies.

1. `hostess add foo.example.dev 127.0.0.1`
2. `hostess add bar.example.dev 127.0.0.1`
3. `lein deps`
4. `lein ring server-headless`
5. Visit `foo.example.dev:3000`
