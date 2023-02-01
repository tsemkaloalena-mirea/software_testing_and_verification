def start_application():
  app = TestedApps.Items[0]
  app.Params.SimpleParams.CommandLineParameters = "NotOpenApp"
  p = app.Run()
  for i in range(10):
    count()
  

def click_number_by_name(btn_name):
  btn = Sys.Process("Microsoft.WindowsCalculator").UIAObject("Калькулятор").UIAObject("LandmarkTarget").UIAObject("Числовая_панель").UIAObject(btn_name)
  btn.Click()

def click_operator_by_name(btn_name):
  btn = Sys.Process("Microsoft.WindowsCalculator").UIAObject("Калькулятор").UIAObject("LandmarkTarget").UIAObject("Стандартные_операторы").UIAObject(btn_name)
  btn.Click()
  
def count():
  click_operator_by_name("Минус")
  click_number_by_name("Два")
  click_number_by_name("Восемь")
  click_operator_by_name("Равно")
  
def close_application():
  app = TestedApps.Items[0]
  app.Close()
  Delay(2000)
  if p.Exists:
    app.Terminate()