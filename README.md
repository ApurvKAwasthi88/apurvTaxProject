# The Tax Project.It helps the client to compute taxes for various countries based on their Tax Rules and generates a receipt for items provided to the api
The TaxOrchestrator is the main class for the api. It computes taxes and generates recipt based on the Items provided to the the Orchestrator. Orchestrator computes taxes based on country, it calls the TaxFactory to get the instaice of TaxBusiness.

### Some of the System Highlights
We are loading the tax rates and loading it to the cache(when the system loads). The cache gets loaded from the property file(tax.prop) in current implementation, this can be changed to load it from the database from a metadata table that stores Item category and its tax rate.
Guava cache is used in the implementation in TaxRepo thath helps us handle various scenario, for example  if there is a cache miss it tries to load it from the datasorce so this can be helpful in the use cases of when we add new tax rates for new items (kind of write aside startegy). We can also have an eviction policy by which the category get's evicited after particular time and the cahce loads it from the datasource this will be helpfull when we update some of the tax rate from the backend for particular category (when there is revision of tax rates).

Additionaly we have a additionalTax.prop that is loaded into cache, this can again be changed to load it from a metadata table from database.
The cache impl used here is Guava Cahche. From the implementation standpoint we check if the item has some add on tax and ifPresent we add this value to the tax computation. This is also configurable and item category can be added to thie datasorce on the fly





## Code Coverage
Tool Used : InteliJ

![taxprojectcodecoverage](https://user-images.githubusercontent.com/38209163/38498746-0b065a82-3c23-11e8-881e-5b25c6ff4391.jpg)

Please note: There are some defualt implementation that are empty and are created from the extensibility prespective. Hecne in some of the packages the coverage is low

