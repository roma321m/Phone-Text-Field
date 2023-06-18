# Phone-Text-Field

Our Phone Text Field View is a tool that can help make the implementation of a <b>'Phone Number View'</b> in your mobile app much easier based on <b>Compose And Material 3</b>.


# Screenshots
>| | | |
>|--|--|--|
>|<img src="https://github.com/DorelSaig/WeatherView/assets/62397127/897ad608-7e44-4461-87a2-37290ae40034" alt="alt text" title="Sample App" width="300" height="650" border="10">|<img src="https://github.com/DorelSaig/WeatherView/assets/62397127/138558ab-ab59-4c8e-ba3a-2ba433b824a6" alt="alt text" title="Sample App" width="300" height="650" border="10">|<img src="https://github.com/DorelSaig/WeatherView/assets/62397127/d5934d0e-e6fa-4a61-af22-f023b415df12" alt="alt text" title="Sample App" width="300" height="650" border="10">|


# Features
>
>The User chooses the country flag he wants to enter the number for, the View will show a template that will hint the user to the correct build of the phone number in the chosen country. 
>if the user enters a valid international phone number the filiation will be successful 

# Usage
>
># 1. Add this library project as a module in Android Studio.
> So your app will be having module dependency of this Lib.
>
># 2. First, in your activity init a variable called "text" which will hold input from the user as follow:
>```
>                    var text {
>                       mutableStateOf("")
>                   }
>  ```
>
># 3. Next, you can use the following code to create and build the view
>
>```
>                    PhoneTextFieldView(
>                        modifier = Modifier,
>                        value = text,
>                        onValueChange = { number ->
>                            text = number
>                        }
>                    )
>  ```

# Enjoy! :)
