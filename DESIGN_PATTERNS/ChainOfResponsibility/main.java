/*

it lets you pass requests along a chain of handlers, allowing each handler to 
decide whether to process the request or pass it to the next handler in the chain.
Applications -> Design logger, 

- A request must be handled by one of many possible handlers, and you don’t want the sender to be tightly coupled to any specific one.
- You want to decouple request logic from the code that processes it.
- You want to flexibly add, remove, or reorder handlers without changing the client code.


whenever a sender sends requests, and he does not care who fulfills that requests,
I have a chain of Receiver, receiving those requests,
Receiver rc1, rc2 , .... 

An example like HTTPRequest, 
Each request carries some information about the user, their role, how many requests 
they have made, and some payload data.

Authentication: Is the user properly authenticated via a token or session?
Authorization: Is the authenticated user allowed to perform this action?
Rate Limiting: Has the user exceeded their allowed number of requests?
Data Validation: Is the request payload well-formed and valid?

- A typical first attempt might look like this: implement all logic inside a 
single class using a long chain of if-else statements.
class RequestHandler {
    public void handle(Request request) {
        if (!authenticate(request)) {
            System.out.println("Request Rejected: Authentication failed.");
            return;
        }

        if (!authorize(request)) {
            System.out.println("Request Rejected: Authorization failed.");
            return;
        }

        if (!rateLimit(request)) {
            System.out.println("Request Rejected: Rate limit exceeded.");
            return;
        }

        if (!validate(request)) {
            System.out.println("Request Rejected: Invalid payload.");
            return;
        }

        System.out.println("Request passed all checks. Executing business logic...");
        // Proceed to business logic
    }

    private boolean authenticate(Request req) {
        return req.user != null;
    }

    private boolean authorize(Request req) {
        return "ADMIN".equals(req.userRole);
    }

    private boolean rateLimit(Request req) {
        return req.requestCount < 100;
    }

    private boolean validate(Request req) {
        return req.payload != null && !req.payload.isEmpty();
    }
}
client code,
public class App {
    public static void main(String[] args) {
        Request req = new Request("john_doe", "ADMIN", 42, "{ \"data\": 123 }");
        RequestProcessor processor = new RequestProcessor();
        processor.handle(req);
    }
}


Why This Approach Breaks Down?
1. Violates the Open/Closed Principle
Every time you need to add a new check, say logging, caching, or metrics collection, 
you must modify the existing RequestProcessor class. The class is open for modification 
when it should be closed for changes and open for extension.

2. Poor Separation of Concerns
All validation and control logic is tightly coupled inside a single method. 
This violates the Single Responsibility Principle. The class is doing too many things: 
authentication, authorization, rate limiting, validation, and business logic coordination.

3. No Reusability
What if another service needs the same authentication logic? You would have to copy the 
code or create awkward shared methods. Neither option is clean.

--------

1. Handler (Interface)
Declares the common interface for all handlers in the chain.
It defines how to set the next handler and how to process a request.

The interface defines the chaining contract. Every handler in the chain can be 
treated uniformly, whether it is an authentication check, a rate limiter, or the 
final business logic handler.

2. ConcreteHandlers (e.g., AuthHandler, RateLimitHandler)
Each concrete handler implements one processing step. It decides whether to handle the 
request, reject it, or pass it to the next handler.

3. Client
Builds the chain by linking handlers together and sends the initial request to the first
handler. The client is unaware of which handler ultimately processes the request



*/

class Request {
    public String user;
    public String userRole;
    public int requestCount;
    public String payload;

    public Request(String user, String role, int requestCount, String payload) {
        this.user = user;
        this.userRole = role;
        this.requestCount = requestCount;
        this.payload = payload;
    }
}

interface RequestHandler {
    void setNext(RequestHandler next);
    void handle(Request request);
}

abstract class BaseHandler implements RequestHandler {
    protected RequestHandler next;

    @Override
    public void setNext(RequestHandler next) {
        this.next = next;
    }

    protected void forward(Request request) {
        if (next != null) {
            next.handle(request);
        }
    }
}

class AuthHandler extends BaseHandler {
    @Override
    public void handle(Request request) {
        if (request.user == null) {
            System.out.println("AuthHandler: User not authenticated.");
            return;
        }
        System.out.println("AuthHandler: Authenticated.");
        forward(request);
    }
}

class AuthorizationHandler extends BaseHandler {
    @Override
    public void handle(Request request) {
        if (!"ADMIN".equals(request.userRole)) {
            System.out.println("AuthorizationHandler: Access denied.");
            return;
        }
        System.out.println("AuthorizationHandler: Authorized.");
        forward(request);
    }
}

class BusinessLogicHandler extends BaseHandler {
    @Override
    public void handle(Request request) {
        System.out.println("BusinessLogicHandler: Processing request for " + request.user + "...");
    }
}



public class main {
    public static void main(String args[])
    {
        BaseHandler auth = new AuthHandler();
        BaseHandler authorization = new AuthorizationHandler();
        BaseHandler businessLogic = new BusinessLogicHandler();

        // Build the chain
        auth.setNext(authorization);
        authorization.setNext(businessLogic);

        // Send a valid request through the chain
        Request request = new Request("john", "ADMIN", 10, "{ \"data\": \"valid\" }");
        auth.handle(request);

        System.out.println("\n--- Trying an invalid request ---");
        Request badRequest = new Request(null, "USER", 150, "");
        auth.handle(badRequest);
    }
}