package components

@OptIn(ExperimentalWasmJsInterop::class)
actual val copyrightYear: String = js("new Date().getFullYear().toString()")
