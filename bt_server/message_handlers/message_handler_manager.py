

class MessageHandlerManager:

    __handlers = []

    def add_handler(self, handler):
        self.__handlers.append(handler)

    def handle(self, message):
        for i in range(len(self.__handlers)):
            if self.__handlers[i].can_handle(message):
                self.__handlers[i].handle(message)
